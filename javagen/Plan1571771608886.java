public class Plan1571771608886 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
if ( DecreaseDimmer("A") ) {
IncreaseDimmer("B");
} else {
DecreaseTraffic("A");
}

}

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}



}
}
