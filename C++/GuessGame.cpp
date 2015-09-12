#include <iostream>
#include <cstdlib> // for srand() and rand()
#include <ctime> // for time(NULL) in srand()

using namespace std;

int main(int argc, char *argv[])
{
    int max_num = 50;
    int max_tries = 10;

    int random_number;
    int guess = 0;
    int current_tries = 0;

    int low_guess = 1;
    int high_guess = max_num;

    srand(time(NULL));
    random_number = rand() % max_num + 1;

    while(current_tries < max_tries) {
        cout << "Enter a number from 1 to " << max_num << " (" <<  max_tries-current_tries << " tries left - between "
        << low_guess << " and " << high_guess << "): ";
        cin >> guess;

        if(guess == random_number) {
            cout << "Congratulations! You guessed correctly!" << endl;
            break;
        }

        if(guess > random_number) {
            cout << "Too HIGH!!" << endl;
            if(guess < high_guess || high_guess == max_num) {
                high_guess = guess;
            }
        }

        if(guess < random_number) {
            cout << "Too LOW!!" << endl;
            if(guess > low_guess || low_guess == 1) {
                low_guess = guess;
            }
        }

        current_tries++;
        if(current_tries >= max_tries) {
            cout << "Outta tries! Better luck next time. The number was " << random_number << endl;
            break;
        }
    }
    return 0;
}
