public class Plan1571769660854 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
StartServer("C");

if ( StartServer("A") ) {
StartServer("A");
} else {
DecreaseTraffic("A");
}


DecreaseDimmer("A");

for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
}

DecreaseTraffic("A");
StartServer("B");


}


}
}
