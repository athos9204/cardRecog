@echo off
set imageName=2_of_hearts_copy.png
set posNumberSample=1
set posNumberTrain=250
set haarDir=haarcascade
set width=40
set height=58
set mode=%1
set minhitrate=0.999
set maxfalsealarm=0.5

if %mode%==create (
createSample %imageName% %posNumberSample% %width% %height% 
)

if %mode%==train (
train %posNumberTrain% %haarDir% %width% %height% %minhitrate% %maxfalsealarm%
)

	