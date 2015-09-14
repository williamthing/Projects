#include <iostream>
#include <vector>
#include <map>
#include <cstdlib>
#include <ctime>
#include <string>
#include <locale>

/*    Design a hangman-type game on paper first.
    Then build it here. It can be a rough first attempt...
*/

using namespace std;

int GetRandomElement(int max);
string strtolower(string str);
string ShowLettersUsed(map<string, int> letters);
string RevealWord(string hidden_word, string word, string letter);

int main(int argc, char *argv[])
{
    int total_possible_guesses = 10;
    vector<string> possible_words;
    possible_words.push_back("Mike");
    possible_words.push_back("Aimy");
    possible_words.push_back("Computer");
    possible_words.push_back("Television");
    possible_words.push_back("Movie");
    possible_words.push_back("Book");
    possible_words.push_back("Wedding");
    
    string word = possible_words[GetRandomElement(possible_words.size())];
    word = strtolower(word);

    string hidden_word = string(word.length(), '-');

    map<string, int> letters_used; // Keep track of individual letters used.
    int guesses = 0; // Keep track of total guesses used.
    string letter; // Buffer for the letter entered in the loop.

    // Greeting / instructions.
    cout << "A word has been selected at random... it is " << word.length() << " characters long. " << endl
    << "You have " << total_possible_guesses << " incorrect guesses allowed. Good luck! " << endl;

    while(1) {
        cout << endl << hidden_word << endl;

        cout << "Guesses remaining: " << total_possible_guesses - guesses << endl;
        cout << "Letters used: " << ShowLettersUsed(letters_used) << endl;
        cout << "Enter a letter: ";
        cin >> letter;
        letter = strtolower(letter);

        if(letters_used[letter]) {
            cout << "Already used that letter!" << endl;
            continue;
        }

        letters_used[letter]++; // Letter was not already entered. Mark it as used.
        // A valid guess was made, increment the counter IF it's not in the word.
        if(word.find(letter) == string::npos) {
            guesses++;
        }

        // Lets re-generate our hidden_word, revealing each occurance of that letter if it exists.
        hidden_word = RevealWord(hidden_word, word, letter);

        // Is the word complete?
        if(word == hidden_word) {
            // Yes!
            cout << "CONGRATULATIONS! YOU WON!!" << endl;
            break;
        }

        // Is the player out of tries?
        if(guesses == total_possible_guesses) {
            cout << "Sorry, but you lost! The word was: " << word << endl;
            break;
        }
    }
    
    return 0;
}

// ##############################################
int GetRandomElement(int max) {
    srand(time(NULL));
    
    return rand() % max;
}

// #############################################
string strtolower(string str) {
    locale loc;
    string retval = str;

    for(size_t i = 0; i < str.length(); i++) {
        retval[i] = tolower(str[i], loc);
    }

    return retval;
}

// ##############################################
string ShowLettersUsed(map<string, int> letters) {
    string retval;

    for(map<string, int>::const_iterator it = letters.begin(); it != letters.end(); it++) {
        retval += it->first;
    }

    return retval;
}

// ##############################################
string RevealWord(string hidden_word, string word, string letter) {
    for(unsigned int i = 0; i < hidden_word.length(); i++) {
        if(hidden_word[i] == '-' && word[i] == letter[0]) {
            // This occurance should be revealed.
            hidden_word[i] = letter[0];
        }
    }

    return hidden_word;
}
