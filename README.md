# Poof-O-Floof
* 11/25 Agreed on the following directions.
1. Use Java to fetch ip-geolocation data and petfinder.com
2. Use "photo" as a basis of user interaction. The app will generate multiple photos from single animal object in the petfinder.com JSON response. The phto POJO has: photoId, animalId and url. Only the large-size url is stored.
3. animalId on petfinder.com seems to be an 8-digit number. The phto id will be a 10-digit number. The last two digit marks the photos. e.g animalId 12345678 => photoIds: 1234567801, 1234567802, 1234567803 etc.
