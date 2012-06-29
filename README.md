## What

Showcase a bug when using a [Tika](http://tika.apache.org/) based [StringBridge](http://docs.jboss.org/hibernate/stable/search/api/org/hibernate/search/bridge/StringBridge.html) 

## Implementation

The implementation is at  [ByteArrayBridge.java](https://github.com/framiere/hibernate-search-tika/blob/master/src/main/java/fr/ramiere/hibernate/search/ByteArrayBridge.java)

## Unit test
When unit-testing this StringBrige everything works fine. 

 [ByteArrayBridgeTest](https://github.com/framiere/hibernate-search-tika/blob/master/src/test/java/fr/ramiere/hibernate/search/ByteArrayBridgeTest.java)

## Integration Test
When integration-testing this StringBriget some element does not work.
 
 [ValidBookTest.java](https://github.com/framiere/hibernate-search-tika/blob/master/src/test/java/fr/ramiere/hibernate/search/ValidBookTest.java)
 [InvalidBookTest.java](https://github.com/framiere/hibernate-search-tika/blob/master/src/test/java/fr/ramiere/hibernate/search/InvalidBookTest.java)

## Bug report

The hibernate search associated bug report is at

 https://hibernate.onjira.com/browse/HSEARCH-1171