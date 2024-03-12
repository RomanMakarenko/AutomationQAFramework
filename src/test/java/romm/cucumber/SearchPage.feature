@tag
  Feature: Search page functionality

    Background:
    Given Open search page

    @tag2
    Scenario Outline: Search result contains articles for response
      When Search by <searchText>
      Then Response should contains article id <articleId>
      Examples:
        | searchText | articleId |
        | Shevchenko | 9861      |
        | Lviv       | 1773916   |
        | Shevchenko | 333       |
