#Plagiarism Dection

##To Run


    $make                   #to clean, compile and then run with default file names.  

    $make compile           #to compile

    $make run               #to run  
    
    $make clean             #clean all .class  
    
    $bash run.sh  [synonymous_name] [file1_name] [file2_name] {tuple_size}    #to run with args  

##Input Files

- input files should be put under ``src/``  
- synonymous file can contain multiple lines, each line is consider as a synonymous set. 
- one word belong to different synonymous set. 
- e.g "take" belong to [take,buy,get, shop] and [take,bring, carry] while *bring* and *shop* considerd as different words.

##Directory 

- ``res/`` : file1, file2 and synonymous file.
- ``bin/`` : compiled .class
- ``src/`` : source code
    - ``src/.../main/`` : main
    - ``src/../util/``  : file IO tool
    - ``src/../test ``  : unit test

##Platform
- ubuntu 
- compiled with javac 1.8.0_101
- run with Java(TM) SE Runtime Environment (build 1.8.0_101-b13)
