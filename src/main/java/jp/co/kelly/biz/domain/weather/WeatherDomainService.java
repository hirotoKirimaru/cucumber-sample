package jp.co.kelly.biz.domain.weather;

import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class WeatherDomainService {
  private final CsvMapper csvMapper;

  public List<Weather> readCsc(Path path) throws IOException {
    CsvSchema csvSchema = csvMapper.schemaFor(Weather.class).withHeader();
    List<Weather> rtn = new ArrayList<>();

    MappingIterator<Weather> objectMappingIterator = csvMapper.readerFor(Weather.class).with(csvSchema).readValues(
            path.toFile()
    );

    while (objectMappingIterator.hasNext()) {
      rtn.add(
          objectMappingIterator.next()
      );
    }

    return rtn;
  }
}
