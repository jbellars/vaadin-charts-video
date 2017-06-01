package org.vaadin.miki.charts.intro;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataProviderSeries;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.miki.charts.data.mock.MockDataService;
import org.vaadin.miki.charts.data.model.Book;
import org.vaadin.miki.charts.data.model.BookPrice;

import javax.servlet.annotation.WebServlet;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("introtheme")
@Widgetset("org.vaadin.miki.charts.intro.IntroChartsWidgetset")
public class IntroChartsUI extends UI {

	private MockDataService service = new MockDataService();
    private List<Chart> chartConfigurationList;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        chartConfigurationList = new ArrayList<Chart>();
        buildListOfChartsAndConfigurations();

        Collection<Book> books = service.getBooks();

        List<DataProviderSeries<BookPrice>> seriesList = new ArrayList<DataProviderSeries<BookPrice>>();

        for(Book book: books) {
            seriesList.add(returnSeries(book, service));
        }

        for(DataProviderSeries<BookPrice> series : seriesList)
        {
            for(Chart chart : chartConfigurationList)
            {
                Configuration config = chart.getConfiguration();
                config.addSeries(series);
            }
        }

        for (Chart chart : chartConfigurationList)
        {
            layout.addComponents(chart);
        }

        setContent(layout);
    }

    private void buildListOfChartsAndConfigurations()
    {
        addConfigurationToList(ChartType.AREA);
        addConfigurationToList(ChartType.AREARANGE);
        addConfigurationToList(ChartType.AREASPLINE);
        addConfigurationToList(ChartType.AREASPLINERANGE);
        addConfigurationToList(ChartType.BAR);
        addConfigurationToList(ChartType.BOXPLOT);
        addConfigurationToList(ChartType.BUBBLE);
        addConfigurationToList(ChartType.COLUMN);
        addConfigurationToList(ChartType.COLUMNRANGE);
        addConfigurationToList(ChartType.ERRORBAR);
        addConfigurationToList(ChartType.FLAGS);
        addConfigurationToList(ChartType.FUNNEL);
        addConfigurationToList(ChartType.GAUGE);
        addConfigurationToList(ChartType.HEATMAP);
        addConfigurationToList(ChartType.LINE);
        addConfigurationToList(ChartType.PIE);
        addConfigurationToList(ChartType.POLYGON);
        addConfigurationToList(ChartType.PYRAMID);
        addConfigurationToList(ChartType.SCATTER);
        addConfigurationToList(ChartType.SOLIDGAUGE);
        //addConfigurationToList(ChartType.SPARKLINE);
        addConfigurationToList(ChartType.SPLINE);
        addConfigurationToList(ChartType.TREEMAP);
        addConfigurationToList(ChartType.WATERFALL);
    }

    private void addConfigurationToList(ChartType chartType)
    {
        Chart chart = new Chart(chartType);
        Configuration configuration = chart.getConfiguration();
        configuration.setTitle(chartType.toString());
        configuration.setSubTitle("Years 2011-2017, prices in EUR");
        chartConfigurationList.add(chart);
    }

    private static DataProviderSeries<BookPrice> returnSeries(Book book, MockDataService service)
    {
        Collection<BookPrice> prices = service.getPrices(book, 2011, 2012, 2013, 2014, 2015, 2016, 2017);

        // Vaadin 8 no longer has containers - instead we are using Java 8 awesomeness
        ListDataProvider<BookPrice> provider = new ListDataProvider<>(prices);

        DataProviderSeries<BookPrice> series = new DataProviderSeries<>(provider);
        series.setY(BookPrice::getAmount);
        series.setX(BookPrice::getYear);
        series.setName(book.toString());

        return series;
    }

    @WebServlet(urlPatterns = "/*", name = "IntroChartsUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = IntroChartsUI.class, productionMode = false)
    public static class IntroChartsUIServlet extends VaadinServlet {
    }
}
