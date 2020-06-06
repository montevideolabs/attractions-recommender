==================================================================
Theme Park Attraction Visits
==================================================================

Dataset Information: 
This dataset comprises a set of users and their visits to various attractions in five theme parks (Disneyland, Epcot, California Adventure, Disney Hollywood and Magic Kindgom). The user-attraction visits are determined based on geo-tagged Flickr photos that are: (i) posted from Aug 2007 to Aug 2017 and retrieved using the Flickr API; (ii) then mapped to specific attraction location and attraction categories; and (iii) then grouped into individual travel sequences (consecutive user-attraction visits that differ by <8hrs).  Other associated datasets are the "List of Attractions/POIs" dataset ("POI-{themeParkName}.csv" files from "poiList-sigir17.zip") and "Attraction/POI Cost-Profit Table" dataset ("costProfCat-{themeParkName}POI-all.csv" files from "costProf-sigir17.zip").

All user-attraction visits in each theme park are stored in a single csv file that contains the following columns/fields:
 - photoID: identifier of the photo based on Flickr.
 - userID: identifier of the user based on Flickr.
 - dateTaken: the date/time that the photo was taken (unix timestamp format).
 - poiID: identifier of the attraction (Flickr photos are mapped to attraction based on their lat/long).
 - poiTheme: category of the attraction (e.g., Roller Coaster, Family, Water, etc).
 - poiFreq: number of times this attraction has been visited.
 - rideDuration: the normal ride duration of this attraction.
 - seqID: travel sequence no. (consecutive attraction visits by the same user that differ by <8hrs are grouped as one travel sequence).

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
 