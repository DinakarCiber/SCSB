package org.recap.camel.datadump.routebuilder;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.recap.RecapConstants;
import org.recap.camel.datadump.FileNameProcessorForDataDumpFailure;
import org.recap.camel.datadump.FileNameProcessorForDataDumpSuccess;
import org.recap.model.csv.DataDumpFailureReport;
import org.recap.model.csv.DataDumpSuccessReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by premkb on 01/10/16.
 */
@Component
public class DataExportReportFtpRouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(DataExportReportFtpRouteBuilder.class);

    /**
     * Instantiates a new Data export report ftp route builder.
     *
     * @param context                       the context
     * @param ftpUserName                   the ftp user name
     * @param ftpOnlyReportRemoteServer     the ftp only report remote server
     * @param ftpKnownHost                  the ftp known host
     * @param ftpPrivateKey                 the ftp private key
     * @param ftpDumpWithReportRemoteServer the ftp dump with report remote server
     */
    @Autowired
    public DataExportReportFtpRouteBuilder(CamelContext context,
                                           @Value("${ftp.userName}") String ftpUserName, @Value("${ftp.datadump.report.remote.server}") String ftpOnlyReportRemoteServer,
                                           @Value("${ftp.knownHost}") String ftpKnownHost, @Value("${ftp.privateKey}") String ftpPrivateKey,
                                           @Value("${ftp.datadump.remote.server}") String ftpDumpWithReportRemoteServer) {
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from(RecapConstants.DATADUMP_SUCCESS_REPORT_FTP_Q)
                            .routeId(RecapConstants.DATADUMP_SUCCESS_REPORT_FTP_ROUTE_ID)
                            .process(new FileNameProcessorForDataDumpSuccess())
                            .marshal().bindy(BindyType.Csv, DataDumpSuccessReport.class)
                            .to("sftp://" + ftpUserName + "@" + ftpOnlyReportRemoteServer + "?privateKeyFile=" + ftpPrivateKey + "&knownHostsFile=" + ftpKnownHost + "&fileName=${in.header.directoryName}/${in.header.fileName}-${in.header.reportType}-${date:now:ddMMMyyyy}.csv&fileExist=append");
                }
            });

            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from(RecapConstants.DATADUMP_FAILURE_REPORT_FTP_Q)
                            .routeId(RecapConstants.DATADUMP_FAILURE_REPORT_FTP_ROUTE_ID)
                            .process(new FileNameProcessorForDataDumpFailure())
                            .marshal().bindy(BindyType.Csv, DataDumpFailureReport.class)
                            .to("sftp://" + ftpUserName + "@" + ftpOnlyReportRemoteServer + "?privateKeyFile=" + ftpPrivateKey + "&knownHostsFile=" + ftpKnownHost + "&fileName=${in.header.directoryName}/${in.header.fileName}-${in.header.reportType}-${date:now:ddMMMyyyy}.csv&fileExist=append");
                }
            });

            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from(RecapConstants.DATAEXPORT_WITH_SUCCESS_REPORT_FTP_Q)
                            .routeId(RecapConstants.DATAEXPORT_WITH_SUCCESS_REPORT_FTP_ROUTE_ID)
                            .process(new FileNameProcessorForDataDumpSuccess())
                            .marshal().bindy(BindyType.Csv, DataDumpSuccessReport.class)
                            .to("sftp://" + ftpUserName + "@" + ftpDumpWithReportRemoteServer + "?privateKeyFile=" + ftpPrivateKey + "&knownHostsFile=" + ftpKnownHost + "&fileName=${in.header.fileName}.csv&fileExist=append");
                }
            });

            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from(RecapConstants.DATAEXPORT_WITH_FAILURE_REPORT_FTP_Q)
                            .routeId(RecapConstants.DATAEXPORT_WITH_FAILURE_REPORT_FTP_ROUTE_ID)
                            .process(new FileNameProcessorForDataDumpFailure())
                            .marshal().bindy(BindyType.Csv, DataDumpFailureReport.class)
                            .to("sftp://" + ftpUserName + "@" + ftpDumpWithReportRemoteServer + "?privateKeyFile=" + ftpPrivateKey + "&knownHostsFile=" + ftpKnownHost + "&fileName=${in.header.fileName}.csv&fileExist=append");
                }
            });
        } catch (Exception e) {
            logger.error(RecapConstants.ERROR,e);
        }
    }
}
