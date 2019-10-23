public class Plan1571768592054 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}

for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


StartServer("A");

} else {
IncreaseDimmer("C");
}

}



}
}
