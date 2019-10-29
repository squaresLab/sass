public class Plan1571775213364 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseDimmer("A") ) {
if ( IncreaseDimmer("C") ) {
DecreaseTraffic("A");
} else {
IncreaseTraffic("B");
}

} else {
StartServer("C");
}

StartServer("B");
StartServer("B");


StartServer("B");
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}



for (int i = 0; i < 2 ; i++) {
StartServer("C");
StartServer("B");

DecreaseTraffic("A");

}


StartServer("C");

}
}
