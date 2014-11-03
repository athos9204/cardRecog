set imageName=%1
set posNumber=%2
set width=%3
set height=%4

opencv_createsamples -img %imageName% -num  %posNumber% -bg negatives.dat  -vec positiveVector.vec -w %width% -h %height% -maxxangle 1.1 -maxyangle 1.1 -maxzangle  0.5 -bgcolor 0.0 -bgthresh 0 -maxidev 40