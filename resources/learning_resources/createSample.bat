set imageName=%1
set posNumber=%2
set width=%3
set height=%4

opencv_createsamples -img ..\PNG-cards-1.3\%imageName% -num  %posNumber%  -vec positiveVector.vec -w %width% -h %height% -maxxangle 0.0 -maxyangle 0.0 -maxzangle  0.0