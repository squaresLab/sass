public class Plan1571774920484 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
DecreaseDimmer("B");
}

}

StartServer("B");

for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("B");

}



}
}
