
Showcase a bug when using a [Tika](http://tika.apache.org/) based StringBridge % (blob/master/src/test/java/fr/ramiere/hibernate/search/ByteArrayBridgeTest.java)

When unit-testing this StringBrige everything works fine. 
§ (blob/master/src/test/java/fr/ramiere/hibernate/search/ByteArrayBridgeTest.java)

When integration-testing this StringBriget some element does not work. 
. (blob/master/src/test/java/fr/ramiere/hibernate/search/ValidBookTest.java)
. (blob/master/src/test/java/fr/ramiere/hibernate/search/InvalidBookTest.java)

The associated bug report is here : https://hibernate.onjira.com/browse/HSEARCH-1171