# chat-bot
A POC chatbot for acronym questions


How to try 

 http://localhost:4567/all
 
 http://localhost:4567/acronym?question= what does OMG mean
 
 http://localhost:4567/acronym?question= Have you heard of TTYL
 
 Reuse the returned search result in a POST and with the vote set to 1 or -1 
 
 http://localhost:4567/vote
 
 {
   "id" : 14,
   "vote" : -1,
   "positiveVote" : "5",
   "negativeVote" : "1",
   "acronym" : "OMG",
   "text" : "Oh My God"
 } 