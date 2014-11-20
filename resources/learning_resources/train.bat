@echo off
set vecDirectory=./VECs/
set haarDir=./XMLs/

for /F "tokens=*" %%A in (imagesToProcess.txt) do (
if exist %haarDir%%%A  rd  /S/Q %haarDir%%%A  
opencv_haartraining -data %haarDir%%%A  -vec %vecDirectory%%%A_samples.vec  -bg  negatives.dat -nstages 12 -nsplits 2 -minhitrate 0.999 -maxfalsealarm 0.2 -npos 250 -nneg 125 -w 40 -h 58 -nosym -mem 2048 -mode ALL
)