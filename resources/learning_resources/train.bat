@echo off
set posNumber=%1
set haarDir=%2
set width=%3
set height=%4
set minhitrate=%5
set maxfalsealarm=%6
if exist %haarDir%    (
	rd  /S/Q %haarDir%  
)
opencv_haartraining -data %haarDir%   \ -vec samples4.vec -bg  negatives.dat -nstages 20 -nsplits 2 -minhitrate %minhitrate% -maxfalsealarm %maxfalsealarm% -npos %posNumber% -nneg 1500 -w %width% -h %height% -nosym -mem 2048 -mode ALL