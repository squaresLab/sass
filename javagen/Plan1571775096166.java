public class Plan1571775096166 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
for (int i = 0; i < 3 ; i++) {
IncreaseTraffic("B");
}

}

for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
}

DecreaseTraffic("A");

StartServer("A");

}



}
}
