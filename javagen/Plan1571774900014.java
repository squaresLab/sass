public class Plan1571774900014 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

if ( DecreaseTraffic("B") ) {
DecreaseDimmer("A");
} else {
if ( StartServer("A") ) {
for (int i = 0; i < 4 ; i++) {
IncreaseTraffic("A");
}

} else {
IncreaseDimmer("C");
}

if ( IncreaseTraffic("B") ) {
IncreaseTraffic("A");
} else {
for (int i = 0; i < 2 ; i++) {
IncreaseTraffic("B");
}

}


}


}
}
