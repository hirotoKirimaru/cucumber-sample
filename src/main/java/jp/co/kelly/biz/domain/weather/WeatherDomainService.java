package jp.co.kelly.biz.domain.weather;

import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

@RequiredArgsConstructor
public class WeatherDomainService {
  private final CsvMapper csvMapper;

  public List<Weather> readCsc() {
//    CsvSchema csvSchema = csvMapper.schemaFor(Weather.class).withHeader();
//
//
//    InputStream resourceAsStream = getClass().getResourceAsStream("/jp/co/kelly/2014ebina.csv");
////      List<Weather> list =
//    MappingIterator<Weather> objectMappingIterator = null;
//    try {
//      objectMappingIterator = csvMapper.readerFor(Weather.class).with(csvSchema).readValues(resourceAsStream);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
////      MappingIterator<Weather> objectMappingIterator =
//
//      while(objectMappingIterator.hasNext()){
//        Object next = objectMappingIterator.next();
//      }
////      return csvMapper.schema(csvSchema).readValue(resourceAsStream, List.class);
//
////    } catch (Exception e) {
////      e.printStackTrace();
////    }

    return List.of();
  }
}
