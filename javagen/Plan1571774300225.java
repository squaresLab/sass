public class Plan1571774300225 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("C");
}

if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("B");

} else {
DecreaseDimmer("C");
}


}

}
}
