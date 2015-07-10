# Insight-Data-Engineer-Project
A data processing program which counts unique words and compute median number in a stream

Enviromment :JDK 1.7 and the run.sh should work, if not, please leave me a message at yzwunju@gmail.com

===========================================================

For this coding challenge, I will handle input file line by line to get unique words and use my own rolling median algorithm which is optimized for tweets.

## Design Summary

This program consists of two parts:

1. Calculate the total number of times each word has been tweeted.
2. Calculate the median number of *unique* words per tweet, and update this median as tweets come in.

## Details of Implementation

1. HashMap is used to calculate the total number of times each unique word has been tweeted. I can also use Trie, it may occupy less space but will be slower when checking uniqueness.
	1.1 We can not sort a HashMap in java, so I retrieve all the words out of the HashMap to an ArrayList<String>, sort them with my own Comparator and write them in the output file in the right order.
    1.2 When splitting a String by whitespace, need to be carefully about the situation of multiple whitespaces.
    1.3 When write words and their occurrences, the format matters. So I use String.format to get the output in right format.
    
2. My own Rolling Median Algorithm
   We all know that Twitter only allows user to tweet within the length of 140 characters. So the unique words in a tweet is at most 71, for example the tweet may be like: "a b c d ... e f". As a result the range of number of unique words in one tweet is [0,71], for simplicity I use int[140] in the program.
   Algorithm Detail:
   	Now we have a int array of size 140. I will scan the input file line by line, for each line, compute the number of unique words and add 1 to the right position of the int array. For example, the number of unique words in the first tweet is 11, so I will add 1 to the 11th position in the int array.
   	Then we need to compute the median number during the process, this time we will need to know how many tweets we have processed. Let's say, we have processed n tweets, if n is odd, we only need to find which number is the (n/2+1)th one. We can just add numbers from the beginning until the sum becomes equal or larger than (n/2+1), for example we will get 11 after we processed the first line, we need to get (1/2+1)th number which is the first number and we get 11 because the value before 11th are all zero and the value at 11th position is 1. If n is even, we only need to get the mean of two numbers at n/2 and (n/2+1).
   	The time complexity will be at most O(140) which is O(1), so it's extremely fast for any number of tweets, but we should be careful of overflow(number of tweets may be very large), so I changed the Type to long. 

## Concerns

1. To speed up the process if the input file is too large, we can use multi-thread or map reduce. And we need to use ConcurrentHashMap to avoid concurrency issue.
2. If we use multi-thread, we can use concurrent.ExecutorService which has been implemented by java to maintain a tread pool. We will keep reading and once we get 10,000 lines, pass it to a idle thread. But at the same time, we need to label each line to make sure the rolling median number is computed from the correct order.
3. Also I read about a rolling algorithm which use a max heap and min heap, I think in this problem, the numbers are limited from [0,71], so it will not be faster than my algorithm.


