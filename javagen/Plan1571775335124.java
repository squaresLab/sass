public class Plan1571775335124 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
for (int i = 0; i < 4 ; i++) {
DecreaseDimmer("C");
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
DecreaseDimmer("B");
}

} else {

}


}

for (int i = 0; i < 3 ; i++) {
StartServer("B");
}



}
}
