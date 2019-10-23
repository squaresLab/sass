public class Plan1571768807753 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("C");
}

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}



}
}
