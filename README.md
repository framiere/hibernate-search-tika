
Showcase a bug when using a [Tika](http://tika.apache.org/) based StringBridge 

	https://github.com/framiere/hibernate-search-tika/blob/master/src/test/java/fr/ramiere/hibernate/search/ByteArrayBridgeTest.java)

When unit-testing this StringBrige everything works fine. 

	https://github.com/framiere/hibernate-search-tika/blob/master/src/test/java/fr/ramiere/hibernate/search/ByteArrayBridgeTest.java

When integration-testing this StringBriget some element does not work.
 
	https://github.com/framiere/hibernate-search-tika/blob/master/src/test/java/fr/ramiere/hibernate/search/ValidBookTest.java
	https://github.com/framiere/hibernate-search-tika/blob/master/src/test/java/fr/ramiere/hibernate/search/InvalidBookTest.java

The hibernate search associated bug report is here

	https://hibernate.onjira.com/browse/HSEARCH-1171