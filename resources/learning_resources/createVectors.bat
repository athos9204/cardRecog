@echo off
set posImagedirectory=./Positive_image/
set vecDirectory=./VECs/
set datDirectory=./DATs/

if exist createLog.txt    (
	rm  createLog.txt  
)
echo generating DATs...
for /F "tokens=*" %%A in (imagesToProcess.txt) do (
if exist %datDirectory%%%A_positives.dat    (
	rm  %datDirectory%%%A_positives.dat  
)
(echo %posImagedirectory%%%A.png ) >> %datDirectory%%%A_positives.dat
(echo. ) >> %datDirectory%%%A_positives.dat
(echo ## %%A dat generated) >> createLog.txt
)
echo ## % DAT generated!

echo ## generating VECs...
for /F "tokens=*" %%A in (imagesToProcess.txt) do (
perl createtrainsamples.pl %datDirectory%%%A_positives.dat negatives.dat samples 250 "opencv_createsamples  -bgcolor 0 -bgthresh 0 -maxxangle 0.5 -maxyangle 0.5 maxzangle 0.5 -maxidev 40 -w 40 -h 58"
mv  ./samples/Positive_image/%%A.png ./samples/Positive_image/%%A_samples.vec

(echo ## %%A vec generated) >> createLog.txt
)
echo ## VEC generated!

mv ./samples/Positive_image/*.vec %vecDirectory%
rm samples -d -r

echo ## All stuff generated.