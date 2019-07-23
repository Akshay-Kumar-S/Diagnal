# Android MVP architecture example

- This app has a content Listing page, contains a page title and a three-column vertically scrolling grid which is not horizontally scrollable.

- The file CONTENTLISTINGPAGE-PAGE(#NUM).json which is placed in the asset folder of the app is used to provide data for the page (including specifying titles and poster images to be displayed). We will retrieve the data, JSON page at a time as the user scrolls down the page. Not fetching all JSON pages at once, they should be loaded as the user navigates and approaches the end of their current data set. This lazy loading should be done seamlessly without the user noticing that additional data is loading (no pausing of the scroll).
