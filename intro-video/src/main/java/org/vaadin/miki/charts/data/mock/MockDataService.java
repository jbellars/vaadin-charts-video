package org.vaadin.miki.charts.data.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.vaadin.miki.charts.data.BookService;
import org.vaadin.miki.charts.data.PriceService;
import org.vaadin.miki.charts.data.model.Book;
import org.vaadin.miki.charts.data.model.BookPrice;
import org.vaadin.miki.charts.data.model.Person;

public class MockDataService implements PriceService, BookService {

	private final Random random = new Random();

	@Override
	public Collection<BookPrice> getPrices(Book book, int... years) {
		final ArrayList<BookPrice> result = new ArrayList<>();
			for (int year : years) {
				BookPrice price = new BookPrice();
				price.setAmount(20.0f + this.random.nextInt(3000) / 100.0f);
				price.setYear(year);
				price.setBook(book);
				result.add(price);
			}
		return result;
	}

	@Override
	public Collection<Book> getBooks() {
		return Arrays.asList(new Book("Verily, Verily: The KJV - 400 Years of Influence and Beauty", new Person("Jon Sweeney")),
                             new Book("The Memory Code", new Person("Lynne Kelly")),
                             new Book("Parenting Your Powerful Child: Bringing an End to the Everyday Battles",
                                      new Person("Kevin Leman")),
                             new Book(
                                     "Reformation Anglicanism: A Vision for Today's Global Communion",
                                     new Person("Michael Jensen")),
                             new Book("Winds of Jingjiao: Studies on Syriac Christianity in China and Central Asia",
                                      new Person("Li Tang")),
                             new Book(
                                     "Hidden Treasures And Intercultural Encounters: Studies On East Syriac " +
                                             "Christianity In China And Central Asia",
                                     new Person("Dietmar W. Winkler")),
                             new Book("Prayer: Experiencing Awe and Intimacy with God",
                                      new Person("Timothy J. Keller")),
                             new Book("The Day the Revolution Began: Reconsidering the Meaning of Jesus' Crucifixion",
                                      new Person("N.T. Wright")),
                             new Book("The Bruised Reed", new Person("Richard Sibbes")),
                             new Book("The Way of the Dragon or the Way of the Lamb", new Person("Kyle Strobel")));
    }

}
