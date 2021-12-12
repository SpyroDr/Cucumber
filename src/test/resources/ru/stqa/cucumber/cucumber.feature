Feature: Adding and removing products

  Scenario:
    Given '3' are quantity of items that we are going to add
    And category from box of main page - 'Campaigns'
    And '1' is quantity and 'Medium' is size of every item that will add to the cart
    When take and click a item on main page
    Then item page redirects
    When take quantity and size of a item and click to AddToCart button on product page
    Then item are put to the cart
    When put all the selected items to the cart
    Then cart page redirects
    When remove all items from the cart step by step
    Then the cart has no items






