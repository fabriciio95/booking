package com.booking.integrator.core.consumer;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.booking.integrator.core.domain.model.Booking;
import com.booking.integrator.core.domain.model.MessageEventHub;
import com.booking.integrator.core.domain.model.booking.BlNumbers;
import com.booking.integrator.core.domain.model.booking.EquipmentSealNumbers;
import com.booking.integrator.core.domain.model.booking.TariffCommodities;
import com.booking.integrator.core.domain.service.BlNumbersService;
import com.booking.integrator.core.domain.service.BookingService;
import com.booking.integrator.core.domain.service.EquipmentSealNumbersService;
import com.booking.integrator.core.domain.service.TariffCommoditiesService;
import com.booking.integrator.core.service.eventhub.IEventHubService;
import com.booking.integrator.core.service.log.ILogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventprocessorhost.CloseReason;
import com.microsoft.azure.eventprocessorhost.IEventProcessor;
import com.microsoft.azure.eventprocessorhost.PartitionContext;

@Component
public class EventProcessor implements IEventProcessor {

	private static final String EVENT_PROCESSOR_PARTITION = "EVENT_PROCESSOR: Partition ";
	private static final Logger LOGGER = LogManager.getLogger(EventProcessor.class);

	@Autowired
	private BookingService bookingService;

	@Autowired
	private BlNumbersService blNumbService;

	@Autowired
	private TariffCommoditiesService tariffCommoditiesService;

	@Autowired
	private EquipmentSealNumbersService equipmentSealNumbersService;

	@Value("error.id")
	private String errorId;

	@Value("${integrador.eventhub.globe.properties.filter}")
	private String filter;

	private final IEventHubService eventHubService;

	private final ILogService logService;

	private final String propertiesFilter;

	EventProcessor(IEventHubService eventHubService, ILogService logService) {
		this.eventHubService = eventHubService;
		this.logService = logService;
		this.propertiesFilter = filter;
	}

	@Override
	public void onOpen(PartitionContext context) throws Exception {
		LOGGER.info(EVENT_PROCESSOR_PARTITION + context.getPartitionId() + " is opening");
	}

	@Override
	public void onClose(PartitionContext context, CloseReason reason) throws Exception {
		LOGGER.info(
				EVENT_PROCESSOR_PARTITION + context.getPartitionId() + " is closing for reason " + reason.toString());
	}

	@Override
	public void onError(PartitionContext context, Throwable error) {
		StringBuilder sb = new StringBuilder(100);
		sb.append(EVENT_PROCESSOR_PARTITION).append(context.getPartitionId()).append(" onError: ")
				.append(error.toString());
		LOGGER.error(sb.toString());
		this.logService.save(this.errorId, error.toString(), sb.toString(), error.getStackTrace(), null);
	}

	@Override
	public void onEvents(PartitionContext context, Iterable<EventData> events) {
		LOGGER.info(EVENT_PROCESSOR_PARTITION + context.getPartitionId() + " got event batch");

		for (EventData receivedEvent : events) {
			LOGGER.info(EVENT_PROCESSOR_PARTITION + context.getPartitionId() + " checkpointing at "
					+ receivedEvent.getSystemProperties().getOffset() + ","
					+ receivedEvent.getSystemProperties().getSequenceNumber());
			final Optional<MessageEventHub> message = parseEventHubMessage(receivedEvent);
			if (message.isPresent()) {
				ObjectMapper objectMapper = new ObjectMapper();

				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

				try {
					Booking booking = objectMapper.readValue(message.get().getMessage(), Booking.class);
					bookingService.save(booking);
					this.tratarListasString(booking);
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
			LOGGER.info("Mensagem recebida: {}", message.map(m -> m.getMessage()).orElse("mensagem veio nula"));
		}
	}

	private Optional<MessageEventHub> parseEventHubMessage(final EventData receivedEvent) {
		if (receivedEvent.getBytes() != null) {
			String message = new String(receivedEvent.getBytes(), Charset.defaultCharset());
			return Optional.of(new MessageEventHub("Test", message));
		}

		return Optional.empty();
	}

	private void tratarListasString(Booking booking) {

		booking.getBlNumbers().forEach(bln -> {
			BlNumbers blNumbers = new BlNumbers();
			blNumbers.setBlNumbers(bln);
			blNumbers.setBooking(booking);
			blNumbService.save(blNumbers);
		});

		booking.getBookingProducts().forEach(bp -> {
			bp.getTariffCommodities().forEach(tc -> {
				TariffCommodities tariffCommodities = new TariffCommodities();
				tariffCommodities.setBookingProducts(bp);
				tariffCommodities.setTariffCommodities(tc);
				tariffCommoditiesService.save(tariffCommodities);
			});
		});

		booking.getBookingProducts().forEach(bc -> {
			bc.getLineItems().forEach(li -> {
				li.getEquipmentSealNumbers().forEach(esn -> {
					EquipmentSealNumbers equipmentSealNumbers = new EquipmentSealNumbers();
					equipmentSealNumbers.setEquipmentSealNumbers(esn);
					equipmentSealNumbers.setLineItems(li);
					equipmentSealNumbersService.save(equipmentSealNumbers);
				});
			});
		});
	}
}