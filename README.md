# Checkers

**Optimal Heuristic for Checkers**

Kevin Gregor (krg43) Marcus Boeck-Chenevier (mb2533)

**Description &amp; Evaluation:**

 For our project we decided to implement a game playing artificial intelligence. Neither of us have done an artificial intelligence project before so we thought that game playing would be a good introductory project. After looking at a number of board, card, and dice games we narrowed down our choice to the game of checkers. We thought that checkers would be interesting to implement since the chips/piece evolve as the game is play (normal pieces become kings) and this could make for an interesting heuristic function.

 For our implementation it was evident that we would need to use an adversarial search. Additionally we are planning on implementing a minimax algorithm into our artificial intelligence, since this is a turn based game. Now that we decided those two elements of our AI, we needed to think of a good approach to our heuristic function. For this we initially will use a simple function that will take the total count of the AI&#39;s chips minus the total amount of the opponent chips (ie: (# my chips) - (# opponents chips)). But we quickly realized that we would eventually have to modify our heuristic function to add weight to acquiring a king since that is a very advantageous move for the player. This is something we will be exploring throughout our project.

 The &quot;evaluation&quot; of our project is simply how well it plays (against other AI and against humans). Obviously, we would like to maximize the number of games that our AI wins. In order to do this, we are going to modify our heuristic to include a weight for a king piece, as having a king makes more states available with one move and thus allows for more chances to take the opponent&#39;s pieces. We plan to start with this weight being 1 (making the heuristic just # my chips - # opponent&#39;s chips) and increase the weight sequentially. Once we have a few different heuristic functions with different weights, we plan to find which one is optimal by 1. Playing against them ourselves; and 2. Pitting the different AIs against each other. We will record the number of times that a certain AI wins vs the number of times it loses.

 We theorize that the optimal weight for the king piece can be found, as having a king definitely gives a player an advantage, but giving the king a weight much more than a regular chip would make the player prioritize the king too much, leading to scenarios where the player actually hurts their chances of winning by sacrificing regular chips for a king.

**Timeline**

- --4/10 - Create game
- --4/17 - Implement AI with basic heuristic (# your pieces - # their pieces)
- --4/24 - Change heuristic to include weighted king
- --5/01 - Modify and test
- --5/08 - Evaluate data, select best AI
- --5/10 - Write final evaluation/report
