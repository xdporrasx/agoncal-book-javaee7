package org.agoncal.book.javaee7.chapter12;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
@Entity
@NamedQuery(name = "findAllBooks", query = "SELECT b FROM Book b ORDER BY b.id DESC")
public class Book {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Id
  @GeneratedValue
  private Long id;
  @NotNull
  @Size(min = 4, max = 50)
  @Column(nullable = false)
  private String title;
  private Float price;
  @Column(length = 2000)
  private String description;

  private Integer nbOfPage;
  private Boolean illustrations;

  private Language contentLanguage;

  // annotations can be omitted thanks to programming by exception
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "tags")
  private List<String> tags = new ArrayList<>();

  // ======================================
  // =            Constructors            =
  // ======================================

  public Book() {
  }

  public Book(String title, Float price, String description, Integer nbOfPage, Boolean illustrations, Language contentLanguage, String... tags) {
    this.title = title;
    this.price = price;
    this.description = description;
    this.nbOfPage = nbOfPage;
    this.illustrations = illustrations;
    this.contentLanguage = contentLanguage;
    for (String tag : tags) {
      this.tags.add(tag);
    }
  }

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getNbOfPage() {
    return nbOfPage;
  }

  public void setNbOfPage(Integer nbOfPage) {
    this.nbOfPage = nbOfPage;
  }

  public Boolean getIllustrations() {
    return illustrations;
  }

  public void setIllustrations(Boolean illustrations) {
    this.illustrations = illustrations;
  }

  public Language getContentLanguage() {
    return contentLanguage;
  }

  public void setContentLanguage(Language contentLanguage) {
    this.contentLanguage = contentLanguage;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public String getTagsAsString() {
    String s = "";
    for (String tag : tags) {
      s += tag + ", ";
    }
    if (s.length() > 2)
      return s.substring(0, s.length() - 2);
    else
      return s;
  }

  // ======================================
  // =         hash, equals, toString     =
  // ======================================

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Book");
    sb.append("{id=").append(id);
    sb.append(", title='").append(title).append('\'');
    sb.append(", price=").append(price);
    sb.append(", description='").append(description).append('\'');
    sb.append(", nbOfPage=").append(nbOfPage);
    sb.append(", illustrations=").append(illustrations);
    sb.append(", contentLanguage=").append(contentLanguage);
    sb.append(", tags=").append(getTagsAsString());
    sb.append('}');
    return sb.toString();
  }
}