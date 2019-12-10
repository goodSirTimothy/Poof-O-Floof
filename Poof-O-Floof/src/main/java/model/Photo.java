package model;

public class Photo {
	private String animalId;
	private String photoId;
	private String fullUrl;
	public Photo() {
		// TODO Auto-generated constructor stub
	}
	public Photo(String animalId, String photoId, String fullUrl) {
		super();
		this.animalId = animalId;
		this.photoId = photoId;
		this.fullUrl = fullUrl;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((animalId == null) ? 0 : animalId.hashCode());
		result = prime * result + ((fullUrl == null) ? 0 : fullUrl.hashCode());
		result = prime * result + ((photoId == null) ? 0 : photoId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Photo other = (Photo) obj;
		if (animalId == null) {
			if (other.animalId != null)
				return false;
		} else if (!animalId.equals(other.animalId))
			return false;
		if (fullUrl == null) {
			if (other.fullUrl != null)
				return false;
		} else if (!fullUrl.equals(other.fullUrl))
			return false;
		if (photoId == null) {
			if (other.photoId != null)
				return false;
		} else if (!photoId.equals(other.photoId))
			return false;
		return true;
	}
	public String getAnimalId() {
		return animalId;
	}
	public void setAnimalId(String animalId) {
		this.animalId = animalId;
	}
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getFullUrl() {
		return fullUrl;
	}
	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}
	@Override
	public String toString() {
		return "Photo [animalId=" + animalId + ", photoId=" + photoId + ", fullUrl=" + fullUrl + "]";
	}

	
}
