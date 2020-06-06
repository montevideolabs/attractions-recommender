==================================================================
List of Attractions/POIs in Theme Parks
==================================================================

Dataset Information: 
This dataset comprises various attractions/points-of-interest (POI) that are found in each of the five theme parks (Disneyland, Epcot, California Adventure, Disney
Hollywood and Magic Kindgom), along with information such as the POI name, lat/lon coordinates and theme (category). This dataset is used to derive the "Attraction/POI Cost-Profit Table" dataset ("costProfCat-{themeParkName}POI-all.csv" files from "costProf-sigir17.zip") and "Theme Park Attraction Visits Dataset" dataset ("userVisits-{themeParkName}.csv" files from "userVisits-sigir17.zip")

File Description:
There are a total of five files, each named "POI-{themeParkName}.csv", where each row indicate a specific POI and its associated ID, name, lat/long and theme.

The list of POI for each theme park is stored in a single csv file that contains the following columns/fields:
 - poiID: ID of the attraction/POI
 - poiName: name of the attraction/POI
 - rideDuration: duration (minutes) to complete the attraction/ride.
 - lat: latitude coordinates
 - lon: longitude coordinates
 - theme: category of the attraction/POI (e.g., Roller Coaster, Family, Water, etc).

------------------------------------------------------------------
References / Citations
------------------------------------------------------------------
If you use this dataset, please cite the following paper:
 - Kwan Hui Lim, Jeffrey Chan, Shanika Karunasekera and Christopher Leckie. "Personalized Itinerary Recommendation with Queuing Time Awareness". Proceedings of the 40th International ACM SIGIR Conference on Research and Development in Information Retrieval (SIGIR'17). Pg 325-334. Aug 2017.

The corresponding bibtex for this paper is:
 @INPROCEEDINGS { lim-sigir17,
	AUTHOR = {Kwan Hui Lim and Jeffrey Chan and Shanika Karunasekera and Christopher Leckie},
	TITLE = {Personalized Itinerary Recommendation with Queuing Time Awareness},
	BOOKTITLE = {Proceedings of the 40th International ACM SIGIR Conference on Research and Development in Information Retrieval (SIGIR'17)},
	PAGES = {325-334},
	YEAR = {2017}
 }