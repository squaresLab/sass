public class Plan1571768644852 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseDimmer("C") ) {

} else {
StartServer("C");
}

}

StartServer("B");

StartServer("A");
StartServer("C");

for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
DecreaseTraffic("A");
}


}



}
}
