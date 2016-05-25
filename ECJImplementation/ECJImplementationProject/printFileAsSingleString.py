import sys

def main(argv):
    if len(argv) != 2:
        print "invalid use of script"
        print "please specify the file to read from"
    else:
        resultString=""
        with open(argv[1],'r') as fin:
            for line in fin:
                line=line.strip()
                resultString=resultString+" "+line
        print resultString

if __name__=="__main__":
    main(sys.argv)
