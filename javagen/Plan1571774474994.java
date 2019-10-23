public class Plan1571774474994 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("C");
}

} else {
StartServer("B");
}

StartServer("A");

}


}
}
