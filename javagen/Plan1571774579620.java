public class Plan1571774579620 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

} else {
IncreaseTraffic("B");
}

} else {
StartServer("C");
}

DecreaseDimmer("A");

}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
}


}
}
