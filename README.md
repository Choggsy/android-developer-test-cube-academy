# Bonus - Improvements/Other Features

## Task
There were some aspects of the app i ran out of time to complete. Such as having the nomination list
be specific to the logged in user. I made note by these features and left a brief description of
either what blocked me or how i would have implemented this with more time.

-	View their current month's nomination [✔]
-	Create a nomination [✔]

But i've created at least the minimal product and tried to stay as faithful to the figma
designs were possible.

If i had more time i would have liked to unit test the additional classes I made and add UI testing,
I did some reading on Espresso and would have loved to get that working on this project.

## Additional features the app could use:

### Delete Nominations

could be on the Main Activity board to swipe animation deleted nominations which the user made

### Edit Nominations

on the Main Activity, an icon or popup menu with the actions for that nomination like edit - edit
page would look very similar to the submit nomination page.

### Get Nomination by ID

### User Dashboard

Create user dashboard which could contain feature like:

- The Nominations they have left that month (if you'd like to give them an allowance of like 3
  nominations.)
- Any Employee of the month achievements they have received- highlighted on their dashboard.

### Year Recap of Cube of the month

- A way to reflect on final nominee with the Cube of the Month over the course of a year. I imagine
  structures like a list of the months in the year 2024. With the name and picture of the person who
  got Cube of the month that month. With a graphic disabling the upcoming month. with a button to
  make a nomination on the current month nominations are taking place.

### App Navigation

- Additional Views would require a navigation system, a Sticky Navigation bar as part of the Top
  black header best minimal change because it is consistent on the Home Screen designs and the
  Nomination Flow. Although I wouldn't recommend it as the best HCI approach since it reads more as
  a website than an application interface choice.

### Appreciation Notes

- If user can only give one nomination a month, have a way for them to show appreciation to their
  colleagues which doesn't use their 1 nomination. Could be a separate page similar to the figma
  designs with 'Appreciation notes' or since the user must login; notes which only they(and their
  manager for feedback) can see.

### Additional Validation

- At the moment a user can make multiple Nominations for the same Nominee within a month(As Seen on
  01.00 Home Screen in the figma). This would make the nominations received potentially unfair as A
  user can vote for the same person an infinite amount of times

- Validate that a user can not make nominations for themselves.

- The Reasoning should be Mandatory and there is a maximum number of characters but there isn't a
  minimum number character, I would enforce this to prevent people just quickly making nominations
  of no substance. The reason provided would be good data to provide the Nominee as Feedback. So
  this field should be informed to require a set minimum word count
