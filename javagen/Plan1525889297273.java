public class Plan1525889297273 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

for (int i = 0; i < 2 ; i++) {
StartServer("A");
if ( DecreaseTraffic("A") ) {
DecreaseTraffic("A");
} else {
IncreaseDimmer("C");
}

for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {

}

}



}


}
}
