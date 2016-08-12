package com.flp.fms.domain;

import java.io.Serializable;
import java.util.ArrayList;
//import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Film implements Serializable
{
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="film_Id",nullable=false)
	private int filmId;
	
    @Column(name="title",nullable=false)
	private String title;
    
    @Column(name="description",nullable=false)
	private String description;
    
    @Column(name="release_year",nullable=false)
	@Temporal(TemporalType.DATE)
    private Date releaseYear;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name="language_id")
	private Language language;
	
	@Column(name="rental_duration",nullable=false)
	private int rentalDuration;
	
	@Column(name="rental_rate",nullable=false)
	private int rentalRate;
	
	@Column(name="length",nullable=false)
	private int length;
	
	@Column(name="replacement_cost",nullable=false)
	private int replacementCost;
	
	@Column(name="rating",nullable=false)
	private int rating;
	
	@Column(name="special_features",nullable=false)
	private String specialFeatures;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinTable(name = "film_actor", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id") , inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
	private List<Actor> actors = new ArrayList<Actor>();
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinTable(name = "film_category", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id") , inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
	private Category category;
	
	@Column(name="last_update",insertable = false, updatable = false,nullable=false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;
	
	public Film()
	{
		
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Date releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public int getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(int rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(int replacementCost) {
		this.replacementCost = replacementCost;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (rating != other.rating)
			return false;
		if (releaseYear == null) {
			if (other.releaseYear != null)
				return false;
		} else if (!releaseYear.equals(other.releaseYear))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", description=" + description + ", releaseYear="
				+ releaseYear + ", language=" + language + ", rentalDuration=" + rentalDuration + ", rentalRate="
				+ rentalRate + ", length=" + length + ", replacementCost=" + replacementCost + ", rating=" + rating
				+ ", specialFeatures=" + specialFeatures + ", actors=" + actors + ", category=" + category
				+ ", lastUpdate=" + lastUpdate + "]\n";
	}
}