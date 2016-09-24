all: clean compile run
run:
	@java -cp ./bin/ com.tripadvisor.PlagiarismDction.main.Main synonyms file1 file2
compile:
	@javac -d ./bin src/com/tripadvisor/PlagiarismDction/main/*.java  src/com/tripadvisor/PlagiarismDction/util/*.java
clean:
	@rm -rf ./bin/*
