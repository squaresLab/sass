public class Plan1571772055392 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
StartServer("B");
StartServer("A");

} else {
DecreaseDimmer("B");
}

} else {
DecreaseDimmer("A");
}

for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


}

}
}
