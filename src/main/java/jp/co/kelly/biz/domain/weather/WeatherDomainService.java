package jp.co.kelly.biz.domain.weather;

import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.format.SignStyle;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoField.*;

@RequiredArgsConstructor
public class WeatherDomainService {
  private final CsvMapper csvMapper;

  public List<Weather> readCsc(Path path) throws IOException {
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addDeserializer(
        LocalDate.class,
        new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy/M/d"))
    );
    csvMapper.registerModule(javaTimeModule);

    CsvSchema csvSchema = csvMapper
        .schemaFor(Weather.class)
        .withHeader();
    List<Weather> rtn = new ArrayList<>();

    MappingIterator<Weather> objectMappingIterator =
        csvMapper.readerFor(Weather.class)
            .with(csvSchema)
            .readValues(path.toFile());

    while (objectMappingIterator.hasNext()) {
      rtn.add(objectMappingIterator.next());
    }

    return rtn;
  }
}
