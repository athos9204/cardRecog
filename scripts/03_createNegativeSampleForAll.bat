@echo off
set inputDir=..\training\negatives\
set filelist=10_of_clubs
::, 10_of_diamonds, 10_of_hearts, 10_of_spades, 2_of_clubs, 2_of_diamonds, 2_of_hearts, 2_of_spades, 3_of_clubs, 3_of_diamonds, 3_of_hearts, 3_of_spades, 4_of_clubs, 4_of_diamonds, 4_of_hearts, 4_of_spades, 5_of_clubs, 5_of_diamonds, 5_of_hearts, 5_of_spades, 6_of_clubs, 6_of_diamonds, 6_of_hearts, 6_of_spades, 7_of_clubs, 7_of_diamonds, 7_of_hearts, 7_of_spades, 8_of_clubs, 8_of_diamonds, 8_of_hearts, 8_of_spades, 9_of_clubs, 9_of_diamonds, 9_of_hearts, 9_of_spades, ace_of_clubs, ace_of_diamonds, ace_of_hearts, ace_of_spades, black_joker, jack_of_clubs, jack_of_diamonds, jack_of_hearts, jack_of_spades, king_of_clubs, king_of_diamonds, king_of_hearts, king_of_spades, queen_of_clubs, queen_of_diamonds, queen_of_hearts, queen_of_spades, red_joker
set extension=.dat
set outExtension=.vec
set outPosFolder=..\training\output_negative_vec\

::Must be the same as in the haar trainer!
set numberOfSamples=53
set width=30
set height=30

for %%i in (%filelist%) do (
opencv_createsamples -info %inputDir%%%i%extension% -num  %numberOfSamples%   -bg  negatives.dat -vec %outPosFolder%%%i%outExtension% -w %width% -h %height% 
)