# README #

WordGameAssistant by Liv Phillips, Veronica Child, and Adam Tigar for CS257: Software Design at Carleton College. 

WordGameAssistant provides a variety of types of queries a caller can perform on that list of words. For example, a Scrabble or Words With Friends player might want to ask "what's the longest word I can make out of these seven letters?" while someone working on a crossword puzzle might want to know "what five-letter words start with T and end in ST?"

# Usage # 
WordGameAssistant's command-line syntax:
java WordGameAssistant [--dictionary=path-to-dictionary-file] action letters

The legal values for action are: words, use-all-letters, allow-repetition, and regex. These actions correspond to the four WordGameAssistant methods wordsUsingOnlyLetters, wordsUsingAllLetters, wordsUsingLettersAllowingRepetition, and wordsMatchingRegularExpression, respectively. The program prints to standard output (i.e. System.out) a list of words, one per line, ordered from longest word to shortest (and ordered alphabetically for same-length words).

The optional --dictionary flag allows you to explicitly specify your dictionary file. If the --dictionary option is not included, WordGameAssistant tries to use a dictionary file named "dictionary.txt" in the current working directory.
Usage examples

Usage examples:
    The "words" action.
    $ java WordGameAssistant words cra
    arc
    car
    ar

    The "use-all-letters" action.
    $ java WordGameAssistant use-all-letters cra
    arc
    car

    The "allow-repetition" action. (No idea the meanings of oot, otto, and oo, but they're in the file.)
    $ java WordGameAssistant allow-repetition ot
    otto
    toot
    oot
    too
    tot
    oo
    to

    The "regex" action. Note that on a Unix command line, *, ^, and $ are special characters, so to send them to the WordGameAssistant program, we need to put them inside single quotes '...'. You would not include the quotes if providing command-line arguments via IntelliJ.
    $ java WordGameAssistant regex '^st.*nch$'
    staunch
    stanch
    stench
