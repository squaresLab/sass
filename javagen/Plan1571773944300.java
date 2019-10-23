public class Plan1571773944300 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("C");
}



for (int i = 0; i < 2 ; i++) {
StartServer("A");
}


StartServer("B");
StartServer("A");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("C");
}




}

}
}
