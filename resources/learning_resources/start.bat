@echo off
set imageName=2_of_hearts.png
set posNumberSample=1
set posNumberTrain=1
set haarDir=haarcascade
set width=20
set height=20
set mode=%1
set minhitrate=0.99
set maxfalsealarm=0.5

if %mode%==create (
createSample %imageName% %posNumberSample% %width% %height% 
)

if %mode%==train (
train %posNumberTrain% %haarDir% %width% %height% %minhitrate% %maxfalsealarm%
)

	