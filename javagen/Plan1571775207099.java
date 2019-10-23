public class Plan1571775207099 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("B");
}

for (int i = 0; i < 6 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
StartServer("B");
}

}



}
}
