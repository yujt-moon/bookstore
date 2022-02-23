package com.moon.bookstore.influx;//package com.moon.bookstore.influx;
//
//import com.influxdb.client.InfluxDBClient;
//import com.influxdb.client.InfluxDBClientFactory;
//import com.influxdb.client.WriteApi;
//import com.influxdb.client.domain.WritePrecision;
//import com.influxdb.client.write.Point;
//import com.influxdb.query.FluxTable;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.time.Instant;
//import java.util.List;
//
///**
// * @author yujiangtao
// * @date 2021/7/27 上午9:40
// */
//public class InfluxDbTest {
//
//    private static InfluxDBClient client;
//
//    private String bucket = "metric";
//
//    private String org = "enn";
//
//    @BeforeClass
//    public static void init() {
//        String token = "Z27OBdRfYhcm4LM8XLRDREC1nDI3khHGyskJyFd7tUXBisRWR3BXSX8OkWOFDXXI4MBwL1tIh7CO0wJBj5RcqQ==";
//        client = InfluxDBClientFactory.create("http://127.0.0.1:8086", token.toCharArray());
//    }
//
//    @Test
//    public void lineProtocolWrite() {
//        String data = "mem,host=host1 used_percent=23.43234543";
//        try (WriteApi writeApi = client.getWriteApi()) {
//            writeApi.writeRecord(bucket, org, WritePrecision.NS, data);
//        }
//    }
//
//    @Test
//    public void pointWrite() {
//        Point point = Point.measurement("mem")
//                .addTag("host", "host1")
//                .addField("used_percent", 23.43234543)
//                .time(Instant.now(), WritePrecision.NS);
//        try (WriteApi writeApi = client.getWriteApi()) {
//            writeApi.writePoint(bucket, org, point);
//        }
//    }
//
//    @Test
//    public void fluxQuery() {
//        String query = String.format("from(bucket: \"%s\") |> range(start: -1h)", bucket);
//        List<FluxTable> tables = client.getQueryApi().query(query, org);
//    }
//}
