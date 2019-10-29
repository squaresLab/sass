public class Plan1571772060612 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseDimmer("C") ) {
IncreaseTraffic("C");
} else {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


StartServer("C");

}

for (int i = 0; i < 4 ; i++) {
StartServer("A");
}


}

}
}
